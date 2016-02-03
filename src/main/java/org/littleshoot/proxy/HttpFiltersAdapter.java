package org.littleshoot.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.net.InetSocketAddress;

import org.littleshoot.proxy.impl.ResponseUtils;

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
	public HttpResponse clientToProxyRequest(HttpObject httpObject) {
		return null;
	}

	@Override
	public HttpResponse proxyToServerRequest(HttpObject httpObject) {
		return null;
	}

	@Override
	public void proxyToServerRequestSending() {
	}

	@Override
	public void proxyToServerRequestSent() {
	}

	@Override
	public HttpObject serverToProxyResponse(HttpObject httpObject) {
		return httpObject;
	}

	@Override
	public void serverToProxyResponseTimedOut() {
	}

	@Override
	public void serverToProxyResponseReceiving() {
	}

	@Override
	public void serverToProxyResponseReceived() {
	}

	@Override
	public HttpObject proxyToClientResponse(HttpObject httpObject) {
		return httpObject;
	}

	@Override
	public void proxyToServerConnectionQueued() {
	}

	@Override
	public InetSocketAddress proxyToServerResolutionStarted(String resolvingServerHostAndPort) {
		return null;
	}

	@Override
	public void proxyToServerResolutionFailed(String hostAndPort) {
	}

	@Override
	public void proxyToServerResolutionSucceeded(String serverHostAndPort, InetSocketAddress resolvedRemoteAddress) {
	}

	@Override
	public void proxyToServerConnectionStarted() {
	}

	@Override
	public void proxyToServerConnectionSSLHandshakeStarted() {
	}

	@Override
	public void proxyToServerConnectionFailed() {
	}

	@Override
	public void proxyToServerConnectionSucceeded(ChannelHandlerContext serverCtx) {
	}

	@Override
	public DefaultFullHttpResponse writeBadGatewayResponse(HttpRequest httpRequest) {
		String body = "Bad Gateway: " + httpRequest.getUri();
		return ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY, body);
	}

	@Override
	public DefaultFullHttpResponse writeBadRequestResponse(HttpRequest httpRequest) {
		String body = "Bad Request to URI: " + httpRequest.getUri();
		return ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, body);
	}

	@Override
	public DefaultFullHttpResponse writeGatewayTimeoutResponse() {
		String body = "Gateway Timeout";
		return ResponseUtils.responseFor(HttpVersion.HTTP_1_1, HttpResponseStatus.GATEWAY_TIMEOUT, body);
	}

}