package br.com.fiap.pizzatime.user;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="githubuser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    long id;
    String name;
    String avatarUrl;

    public static User convert(OAuth2User githubuser){
        return new User().builder()
                            .id(Long.valueOf(githubuser.getName()))
                            .name(githubuser.getAttribute("name"))
                            .avatarUrl(githubuser.getAttribute("avatar_url"))
                            .build();
    }
    
}
