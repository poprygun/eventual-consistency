org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/messages'
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body([
                id:
                        value(producer(regex('[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}'))),
                message:
                        value(producer(regex('[A-Za-z0-9\\s]+')))
                ]
        )
        headers {
            header(
                    'Content-Type', 'application/json;charset=UTF-8'
            )
        }
    }
}