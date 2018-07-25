package rest;

import dao.CustomerDAO;
import dao.ImplementationType;
import org.springframework.stereotype.Service;
import pojo.CustomerImplementation;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;


@Service
@ApplicationPath("library")
public class Library extends Application {

    @GET
    @Path("/createCustomer/{cpf}/{name}")
    @Produces("text/plain")
    public Response createCustomer(@PathParam("cpf") String cpf, @PathParam("name") String name) {
        CustomerDAO customerDAO = new CustomerDAO();
        Response.ResponseBuilder response = null;
        if (customerDAO.executeOperationForCostumer(new CustomerImplementation().setCpf(cpf).setName(name).setType(ImplementationType.CREATE)))
            response = Response.ok("Customer \n CPF: " + cpf + "\nNome: " + name + "\nCriado com sucesso");
        else
            response = Response.ok("CPF duplicado, customer não adicionado");
        return response.build();
    }

    @GET
    @Path("/delete/{cpf}/")
    @Produces("text/plain")
    public Response deleteCustomer(@PathParam("cpf") String cpf) {
        CustomerDAO customerDAO = new CustomerDAO();
        Response.ResponseBuilder response = null;
        if (customerDAO.executeOperationForCostumer(customerDAO.getLastElementFromList(customerDAO.getEventsFromCPF(cpf)).setType(ImplementationType.DELETE)));
            response = Response.ok("Customer \n CPF: " + cpf + "\nDeletado com sucesso");;
        return response.build();
    }
}
