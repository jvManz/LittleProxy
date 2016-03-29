package org.littleshoot.proxy;

import org.littleshoot.proxy.impl.ResponseUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * Convenience base class for implementations of {@link HttpFilters}.
 */
public class HttpFiltersAdapter implements HttpFilters {
	protected final HttpRequest originalRequest;
	protected final ChannelHandlerContext ctx;

	public HttpFiltersAdapter(HttpRequest originalRequest, ChannelHandlerContext ctx) {
		this.originalRequest = originalRequest;
		this.ctx = ctx;
	}

	public HttpFiltersAdapter(HttpRequest originalRequest) {
		this(originalRequest, null);
	}

	@Override
	public HttpResponse requestPre(HttpObject httpObject) {
		return null;
	}

	@Override
	public HttpResponse requestPost(HttpObject httpObject) {
		return null;
	}

	@Override
	public HttpObject responsePre(HttpObject httpObject) {
		return httpObject;
	}

	@Override
	public HttpObject responsePost(HttpObject httpObject) {
		return httpObject;
	}

	@Override
	public DefaultFullHttpResponse writeBadGatewayResponse(HttpRequest httpRequest) {
		String body = "Bad Gateway: " + httpRequest.getUri();
		DefaultFullHttpResponse response = ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY, body);
		return response;
	}

	@Override
	public DefaultFullHttpResponse writeBadRequestResponse(HttpRequest httpRequest) {
		String body = "Bad Request to URI: " + httpRequest.getUri();
		DefaultFullHttpResponse response = ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, body);
		return response;
	}

	@Override
	public DefaultFullHttpResponse writeGatewayTimeoutResponse() {
		String body = "Gateway Timeout";
		DefaultFullHttpResponse response = ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.GATEWAY_TIMEOUT, body);
		return response;
	}

}
