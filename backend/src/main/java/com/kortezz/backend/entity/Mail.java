package com.kortezz.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "mail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "l_to")
    private String to;
    @Column(name = "l_from")
    private String from;
    @Column(name = "l_subject")
    private String subject;
    @Column(name = "l_text")
    private String text;
    @Column(name = "is_sent")
    private Boolean isSent = false;
}
