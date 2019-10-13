# Server-Sent-Event

* [Server-Sent Events vs. WebSockets](https://stackoverflow.com/questions/5195452/websockets-vs-server-sent-events-eventsource)
* [EventSource](https://developer.mozilla.org/en-US/docs/Web/API/EventSource)

# Cooperating With Nginx

* Please add/modify these setting to the configuration file of nginx:

### proxy_buffering
By default, nginx enable buffering of responses from the proxied server. After the buffering is disabled, every event sent from the server could be passed to a client immediately.

### proxy_cache
Nginx set `proxy_cache` off as default. If you did not configure any other cache setting of nginx, than the `proxy_cache off;` is not need to be added to your nginx config.

### proxy_read_timeout
The default value of this property is 60s, in most of the cases we need longer timeout value for server-sent event. I will use the same value as session timeout.

### References
* [For Server-Sent Events (SSE) what Nginx proxy configuration is appropriate?](https://serverfault.com/questions/801628/for-server-sent-events-sse-what-nginx-proxy-configuration-is-appropriate)
* [EventSource / Server-Sent Events through Nginx](https://stackoverflow.com/questions/13672743/eventsource-server-sent-events-through-nginx)
* [ngx_http_proxy_module](http://nginx.org/en/docs/http/ngx_http_proxy_module.html#proxy_buffering)