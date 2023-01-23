package com.example.erp.entity;

import com.example.erp.entity.enums.PageEnum;
import com.example.erp.entity.enums.PermissionEnum;
import com.example.erp.entity.utils.AbsIntegerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbsIntegerEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PageEnum defaultPage;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    @CollectionTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<PermissionEnum> permissions;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "page", nullable = false)
    @CollectionTable(name = "role_pages", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<PageEnum> pages;
}
