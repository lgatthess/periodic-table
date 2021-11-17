package com.petkov.periodictable.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "ELEMENT")
public class Element {

    @Id
    @Column(name = "ATOMIC_NUMBER", nullable = false, columnDefinition = "NUMBER(10, 0)")
    private Long atomicNumber;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "ALTERNATIVE_NAME", nullable = false, length = 100)
    private String alternativeName;

    @Column(name = "ATOMIC_SYMBOL", nullable = false, length = 10)
    private String atomicSymbol;

    @Column(name = "APPEARANCE", nullable = false, length = 1000)
    private String appearance;

    @Column(name = "DISCOVERY_YEAR", length = 10)
    private String discoveryYear;

    @Column(name = "GROUP", columnDefinition = "NUMBER(10, 0) DEFAULT 0")
    private Integer group;

    @Column(name = "PERIOD", columnDefinition = "NUMBER(10, 0)")
    private Long period;

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("atomicNumber", atomicNumber)
                .append("name", name)
                .append("alternativeName", alternativeName)
                .append("atomicSymbol", atomicSymbol)
                .append("appearance", appearance)
                .append("discoveryYear", discoveryYear)
                .append("group", group)
                .append("period", period)
                .toString();
    }
}
