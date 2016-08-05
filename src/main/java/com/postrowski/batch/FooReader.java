package com.postrowski.batch;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by postrowski on 2016-07-26.
 */
@Named
public class FooReader extends AbstractItemReader
{
    private int ELEMENTS = 100_000;

    private List<Long> values;
    private Iterator<Long> iterator;

    @Override
    public void open( Serializable checkpoint ) throws Exception
    {
        values = new ArrayList<>( ELEMENTS );

        long prefix = (long)Math.random() * ELEMENTS;
        System.out.println( "PREFIX : " + prefix );

        for( int i = 0; i < ELEMENTS; i++ )
        {
            values.add( prefix + i );
        }

        iterator = values.iterator();
    }

    @Override
    public Object readItem() throws Exception
    {
        if( iterator.hasNext() )
        {
            return iterator.next();
        }

        return null;
    }
}
