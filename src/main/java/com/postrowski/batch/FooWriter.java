package com.postrowski.batch;

import com.postrowski.Foo;
import com.postrowski.FooService;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by postrowski on 2016-07-26.
 */
@Named
public class FooWriter extends AbstractItemWriter
{
    @Inject
    private FooService fooService;

    @Override
    public void writeItems( List<Object> items ) throws Exception
    {
        for( Object item : items )
        {
            final Foo foo = new Foo();
            foo.setName( "tmp_value" + item );
            foo.setNumber( (Long)item );

            //sleep(1);
            fooService.persist( foo );

            final long subFooId = ThreadLocalRandom.current().nextLong( foo.getId() );
            final Foo subFoo = fooService.find( subFooId );
            if( subFoo != null )
            {
                subFoo.setSubFooId( foo.getId() );
                fooService.update( subFoo );
            }

            long index = (long)item;
            if( index % 10000 == 0 && index != 0 )
            {
                fooService.flush();
            }

            //fooService.flush();
        }

    }

    private void sleep( long i )
    {
        try
        {
            TimeUnit.SECONDS.sleep( i );
        }
        catch( Exception e )
        {

        }
    }
}
