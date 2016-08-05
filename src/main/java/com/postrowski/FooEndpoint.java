package com.postrowski;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Created by postrowski on 2016-08-02.
 */
@Path( "/foo" )
public class FooEndpoint
{
    @Inject
    private FooService fooService;

    @GET
    @Path( "/import" )
    public Response startImport()
    {
        final JobOperator jobOperator = BatchRuntime.getJobOperator();
        final long jobId = jobOperator.start( "fooJob", new Properties() );

        return Response.ok( "Done" + jobId ).build();
    }

    @GET
    @Path( "/find/{id}" )
    public Response find( @PathParam( "id" ) long id )
    {
        final Foo foo = fooService.find( id );
        return Response.ok( " FINDED " + foo.getId() ).build();
    }

    @GET
    @Path( "/create" )
    public Response create()
    {
        final Foo foo = new Foo();
        foo.setName( "Created " + LocalDateTime.now().toString() );

        fooService.persist( foo );

        return Response.ok( " CREATED " + foo.getId() ).build();
    }


    @GET
    @Path( "/update/{id}" )
    public Response update( @PathParam( "id" ) long id )
    {
        final Foo foo = new Foo();
        foo.setId( id );
        foo.setName( "UPDATED " +  LocalDateTime.now().toString() );
        foo.setVersion( 0L );

        final Foo update = fooService.update( foo );

        return Response.ok( " UPDATED " + update.getId() ).build();
    }
}
