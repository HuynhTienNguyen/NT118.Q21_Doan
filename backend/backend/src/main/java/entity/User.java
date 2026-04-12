package entity;

import java.security.Timestamp;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
     String username;
     String phone;
     String email;
     String password;
     String full_name;
     String role;
     String status;
     String avatar_url;
     Timestamp created_at;
}
