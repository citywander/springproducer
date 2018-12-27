package contracts

org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url '/books'
		body([
			   "id": $(regex('[0-9]{10}')),
               "title": "Alice",
			   "price": 100
		])
		headers {
			contentType('application/json')
		}
	}
	response {
		status OK()
		body([
			   "errorCode": "123",
			   "errorMessage": "Price too large"
		])
		headers {
			contentType('application/json')
		}
	}
}