package com.sharingif.cube.web.exception.handler;

import com.sharingif.cube.core.exception.ICubeException;
import com.sharingif.cube.core.exception.UnknownCubeException;
import com.sharingif.cube.core.method.HandlerMethod;
import com.sharingif.cube.core.method.exception.handler.AbstractCubeHandlerMethodExceptionHandler;
import com.sharingif.cube.core.request.RequestInfo;

/**
 * CubeExceptionHandler
 * 2015年8月8日 上午11:32:28
 * @author Joly
 * @version v1.0
 * @since v1.0
 */
public class CubeExceptionHandler extends AbstractCubeHandlerMethodExceptionHandler<WebRequestInfo,WebExceptionContent> {
	
	private String defaultErrorView="DefaultExceptionView";
	
	public String getDefaultErrorView() {
		return defaultErrorView;
	}
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	@Override
	public boolean supports(Exception exception) {
		return true;
	}

	/**
	 * 非ICubeException异常转换为UnknownCubeException
	 * @param exception : 异常
	 */
	@Override
	public ICubeException convertException(Exception exception) {
		if(exception instanceof ICubeException)
			return (ICubeException)exception;
			
		return new UnknownCubeException(exception);
	}
	
	@Override
	public WebExceptionContent handlerException(RequestInfo<WebRequestInfo> requestInfo, HandlerMethod handlerMethod, ICubeException cubeException) {
		
		WebExceptionContent out = new WebExceptionContent();
		out.setViewName(defaultErrorView);
		
		return out;
	}

}
