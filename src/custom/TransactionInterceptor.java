package custom;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionInterceptor {
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception{
		System.out.println("Abrindo transação");
		manager.getTransaction().begin();
		
		Object resultado = ctx.proceed();
		
		manager.getTransaction().commit();
		System.out.println("Comitando transação");
		
		return resultado;
	}

}
