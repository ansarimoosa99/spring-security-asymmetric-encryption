package com.ansari.app.role;


import com.ansari.app.common.BaseEntity;
import com.ansari.app.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder   //changed
@Table(name = "ROLES")
public class Role extends BaseEntity {

    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
