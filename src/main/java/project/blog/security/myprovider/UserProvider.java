package project.blog.security.myprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.blog.security.service.FormUserService;

@RequiredArgsConstructor
public class UserProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final FormUserService formUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails findUser = formUserService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, findUser.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치 하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, findUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
