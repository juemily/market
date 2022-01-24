# Market Api

is an Java Api to apply promotions to certain products.

## Installation

to run the Api for first time use the next commands

```bash
mvn install
mvn spring-boot:run
```

## Usage

you need to go to the url http://localhost:8080/bills/bill

then the following body must be applied, it can be modified as needed
```Json
[
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "GR1",
        "productName": "geen tea",
        "productUnitPrice": 3.11
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "SR1",
        "productName": "Strawberry",
        "productUnitPrice": 5.00
    },
    {
        "productCode": "CF1",
        "productName": "Coffee",
        "productUnitPrice": 11.23
    },
    {
        "productCode": "CF1",
        "productName": "Coffee",
        "productUnitPrice": 11.23
    },
    {
        "productCode": "CF1",
        "productName": "Coffee",
        "productUnitPrice": 11.23
    }
]
```

## Docs
you can see the swagger of the project in the following url

http://localhost:8080/swagger-ui.html#/bills-controller# Market Api

