package Apps;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.Calculator;

@Stateless
@Path("calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorService {
	
	@PersistenceContext(unitName = "hello") //unit name because of the name in the persistence.xml
	EntityManager entityManager;
	
	@POST
	@Path("calculate")
	public Response doCalculation(Calculator c)
	{
		Response response;
		try {
		entityManager.persist(c);
		int result = c.Calculation();
		response = Response.ok().entity("{\"Result\": " + result + "}") 
					.type(MediaType.APPLICATION_JSON).build();
		}
		catch (Exception e) {
	        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                    .entity("{\"msg\": "+ e.getMessage() + "\"}") 
	                    .type(MediaType.APPLICATION_JSON).build();
	    }
		return response;	
	}
	
	@GET
	@Path("calculations")
	public List<Calculator> returnCalculations()
	{
		TypedQuery<Calculator> calcList = entityManager.createQuery("SELECT c from Calculator c",Calculator.class);
		return calcList.getResultList();
	}
}
