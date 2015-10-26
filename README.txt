1. This archetype uses jaxrs because SpringMVC does not conform to jaxrs specifications.

2. Deployment descriptor is built using Java code instead of web.xml.

3. Use the HEAD method to get the list of Headers to provide to the service.

4.  URL's:
             http://{HOSTNAME}:{PORT}/{CONTEXT ROOT}/api/person
	HEAD: 	 http://localhost:8091/starterspcxfrestplus/api/person
	OPTIONS: http://localhost:8091/starterspcxfrestplus/api/person
	GET:     http://localhost:8091/starterspcxfrestplus/api/person
	GET:     http://localhost:8091/starterspcxfrestplus/api/person?offset=100&limit=50
	PUT:     http://localhost:8091/starterspcxfrestplus/api/person
	POST:    http://localhost:8091/starterspcxfrestplus/api/person
	DELETE:  http://localhost:8091/starterspcxfrestplus/api/person