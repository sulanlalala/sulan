package com.api.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 过滤器
 * @author Administrator
 *
 */
public class AccessFilter extends ZuulFilter{

	/**
	 * 判断该过滤器是否需要被执行。这里我们直接返回了true。因此该过滤器对所有的请求都会生效。实际运用中我们可以利用该函数
	 * 来指定过滤器的有效范围
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 过滤器的具体操作
	 */
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext ctx=RequestContext.getCurrentContext();
		System.out.println(ctx.toString());
		HttpServletRequest request=ctx.getRequest();
		System.out.println(request.getMethod()+","+request.getRequestURL().toString());
		Object accessToken=request.getParameter("accessToken");
		if(accessToken==null){
			System.out.println("accessToken is null");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		System.out.println("accessToken is ok");
		return null;
	}

	/**
	 * 过滤器的类型，他决定过滤器在请求的哪个生命周期执行。这里为pre，代表会在请求被路由之前执行
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	/**
	 * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回值来依次执行
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
