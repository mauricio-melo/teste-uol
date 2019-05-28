package com.uol.client.domain;

import com.uol.client.commons.Auditable;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "client")
public class Client extends Auditable implements Serializable {

    private static final long serialVersionUID = 932141165141585512L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_client")
    private Long id;

    @Column(name = "nam_client")
    private String name;

    @Column(name = "age_client")
    private Integer age;

    @Builder.Default
    @Column(name = "flg_enabled")
    private Boolean enabled = true;
}
