package com.postrowski;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by postrowski on 2016-08-02.
 */
@Entity
@Cacheable
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE, region = "foos" )
public class Foo
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    //@Column( unique = true )
    private long number;

    @Column(nullable = true)
    private Long subFooId;

    private String name;

    @Version
    private Long version;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public long getNumber()
    {
        return number;
    }

    public void setNumber( long number )
    {
        this.number = number;
    }

    public Long getSubFooId()
    {
        return subFooId;
    }

    public void setSubFooId( Long subFooId )
    {
        this.subFooId = subFooId;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "Foo{" +
            "id=" + id +
            ", subFooId=" + subFooId +
            '}';
    }
}
