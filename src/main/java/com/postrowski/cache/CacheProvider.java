package com.postrowski.cache;

import com.postrowski.Foo;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.TransactionMode;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by postrowski on 2016-07-27.
 */
@ApplicationScoped
public class CacheProvider
{
    private EmbeddedCacheManager entityCacheManager;

    @PostConstruct
    private void init()
    {
        final GlobalConfiguration globalConfig =
            new GlobalConfigurationBuilder().nonClusteredDefault().globalJmxStatistics()
                .allowDuplicateDomains( true ).build();

        final Configuration entityDefaultConfig =
            new ConfigurationBuilder().transaction().transactionMode( TransactionMode.TRANSACTIONAL )
                .eviction().strategy( EvictionStrategy.NONE ).build();

        entityCacheManager = new DefaultCacheManager( globalConfig, entityDefaultConfig );
    }

    @Produces
    public Cache<Long, Foo> createEntityCache()
    {
        final Cache<Long, Foo> entityCache = entityCacheManager.getCache( "EntityCache" );
        return entityCache;
    }


    //@Produces
    //public UniqueIndex getUniqueIndex()
    //{
        //final Cache<Long, Boolean> cache = entityCacheManager.getCache( "TEST_CACHE" );
        //return new UniqueIndex( cache );
    //}
}
