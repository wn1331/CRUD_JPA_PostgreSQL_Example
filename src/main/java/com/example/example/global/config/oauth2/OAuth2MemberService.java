package com.example.example.global.config.oauth2;

import com.example.example.domain.Role;
import com.example.example.domain.user.User;
import com.example.example.global.config.oauth2.memberInfo.GoogleMemberInfo;
import com.example.example.global.config.oauth2.memberInfo.KakaoMemberInfo;
import com.example.example.global.config.oauth2.memberInfo.OAuth2MemberInfo;
import com.example.example.infrastructure.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2MemberService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    /**
     * 정보를 받아오는 메서드
     *
     * @param userRequest
     * @return oAuth2User
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        System.out.println("LoadUser 실행됐다");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2MemberInfo memberInfo = null;

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (registrationId.equals("google")) {
            System.out.println(oAuth2User.getAttributes());
            memberInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            System.out.println(oAuth2User.getAttributes());
            memberInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("로그인 실패");
        }
        String provider = memberInfo.getProvider();
        String providerId = memberInfo.getProviderId();
        String username = memberInfo.getName();
        String email = memberInfo.getEmail();

        final Optional<User> findMember = userRepository.findByEmail(username);
        User user = null;
        if (findMember.isEmpty()) {
            user = User.builder()
                .name(username)
                .email(email)
//                .password(encoder.encode("password"))
                .role(Role.ADMIN)
                .provider(provider)
                .providerId(providerId)
                .build();
            userRepository.save(user);
        } else {
            user = findMember.get();
        }
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }


}
