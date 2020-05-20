package ru.azamat.testwebriotapi.entity;

import javax.persistence.Entity;

import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserLeagueOfLegendsAccount extends UserAccount {

    @Transient
    private String puuid;
    @Transient
    private long summonerLevel;
    private String verificationCode;
}
