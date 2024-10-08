package resource;

import entities.Category;
import entities.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.WarehouseService;
import java.util.List;
import org.jboss.logging.Logger;

@Path("/warehouse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WarehouseResource {

    private static final Logger logger = Logger.getLogger(WarehouseResource.class);

    @Inject
    private WarehouseService warehouseService;

    @GET
    @Path("/products")
    public Response getAllProducts() {
        List<Product> products = warehouseService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/products/{id}")
    public Response getProductById(@PathParam("id") int id) {
        Product product = warehouseService.getProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
    }

    @GET
    @Path("/products/category/{category}")
    public Response getProductsByCategory(@PathParam("category")Category category) {
        List<Product> products = warehouseService.getProductByCategory(category);
        return Response.ok(products).build();
    }

    @POST
    @Path("/products")
    public Response addProduct(Product product) {
        logger.infof("Adding new product: %s", product);

        try {
            warehouseService.addProduct(product);
            return Response.status(Response.Status.CREATED).entity("Product added successfully").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
