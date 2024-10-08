package org.example.resource;

import entities.Category;
import entities.Product;
import service.WarehouseService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/warehouse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WarehouseResource {

    @Inject
    private WarehouseService warehouseService;

    @POST
    @Path("/products")
    public Response addProduct(Product product) {
        System.out.println("POST method called with product: " + product);
        if (product == null) {
            System.out.println("Product is null");
            return Response.status(Response.Status.BAD_REQUEST).entity("Product cannot be null").build();
        }
        try {
            warehouseService.addProduct(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/test")
    public Response testEndpoint() {
        System.out.println("Test endpoint called");
        return Response.ok("Test successful").build();
    }
//MySecureP@ssw0rd

}
