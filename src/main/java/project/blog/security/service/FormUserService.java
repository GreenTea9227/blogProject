package project.blog.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.blog.dto.loginuser.LoginUserDetails;
import project.blog.repository.UserRepository;
import project.blog.vo.User;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FormUserService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("유저 없음");
        }

        User findUser = optionalUser.get();


        // 그냥 usernamePasswordtoken으로  반환 가능?
        return LoginUserDetails
                .builder().username(findUser.getUsername())
                .password(findUser.getPassword())
                .role(findUser.getRole().getRoleName())
                .build();
    }
}
