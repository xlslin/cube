package com.sharingif.cube.core.method.handler.mapping;

import java.util.Comparator;
import java.util.Set;

import com.sharingif.cube.core.method.handler.request.RequestMappingInfo;
import com.sharingif.cube.core.request.RequestInfo;

/**
 * Abstract base class for classes for which {@link RequestMappingInfo} defines
 * the mapping between a request and a handler method.
 *
 * 2015年7月2日 下午8:33:12
 * @author Joly
 * @version v1.0
 * @since v1.0
 */
public abstract class RequestMappingInfoHandlerMapping extends AbstractHandlerMethodMapping<RequestMappingInfo> {
	
	/**
	 * Get the URL path patterns associated with this {@link RequestMappingInfo}.
	 */
	@Override
	protected Set<String> getMappingPathPatterns(RequestMappingInfo info) {
		return info.getPatternsCondition().getPatterns();
	}
	
	/**
	 * Check if the given RequestMappingInfo matches the current request and
	 * return a (potentially new) instance with conditions that match the
	 * current request -- for example with a subset of URL patterns.
	 * @return an info in case of a match; or {@code null} otherwise.
	 */
	@Override
	protected RequestMappingInfo getMatchingMapping(RequestMappingInfo info, RequestInfo<?> requestInfo) {
		return info.getMatchingCondition(requestInfo);
	}
	
	/**
	 * Provide a Comparator to sort RequestMappingInfos matched to a request.
	 */
	@Override
	protected Comparator<RequestMappingInfo> getMappingComparator(final RequestInfo<?> requestInfo) {
		return new Comparator<RequestMappingInfo>() {
			@Override
			public int compare(RequestMappingInfo info1, RequestMappingInfo info2) {
				return info1.compareTo(info2, requestInfo);
			}
		};
	}

}
