package com.machines.vending.infraestructure.persistence.deposits;

import com.machines.vending.infraestructure.persistence.DBEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "deposit")
public class DepositEntity extends DBEntity {
    @Id
    private Integer id;
    private int buyerId;
    private int amount;
}
