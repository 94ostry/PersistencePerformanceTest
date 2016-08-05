package com.postrowski;

//import org.hibernate.Session;
//import org.hibernate.Session;
import org.infinispan.Cache;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * Created by postrowski on 2016-08-02.
 */
@Stateless
public class FooService
{
    @PersistenceContext
    private EntityManager em;

    private static boolean PRINT = false;


    //@Inject
    //private Cache<Long, Foo> cacheEntity;

    public void persist( Foo foo )
    {
        em.persist( foo );
        //cacheEntity.put( foo.getId(), foo );
        if( PRINT )
        System.out.println("Created " + foo.toString());
    }

/*    public Foo find( long id )
    {
        final Optional<Foo> fooOptional =
            cacheEntity.values().stream().filter( foo -> foo.getId() == id ).findFirst();

        return fooOptional.isPresent() ? fooOptional.get() : null;
    }*/



    public Foo find( long id )
    {
        final Foo foo = em.find( Foo.class, id );

        if( PRINT )
        System.out.println("Finded " + ( foo != null ? foo.toString() : " null " ) );



        //System.out.println( ((Session)em.getDelegate()).getSessionFactory().getStatistics().getEntityFetchCount() );
        //System.out.println( ((Session)em.getDelegate()).getSessionFactory().getStatistics().getSecondLevelCacheMissCount() );
        //System.out.println( ((Session)em.getDelegate()).getSessionFactory().getStatistics().getSecondLevelCacheHitCount() );


        return foo;
    }

    public Foo update( Foo foo )
    {
        final Foo merge = em.merge( foo );
        //cacheEntity.put( merge.getId(), merge );

        if( PRINT )
        System.out.println("Updated " + merge.toString());

        return merge;
    }

    public void flush()
    {
        em.flush();
        em.clear();

        if( PRINT )
        System.out.println("Flushed");
    }

    //public void startImport
}
