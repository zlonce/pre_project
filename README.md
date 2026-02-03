# 주문 관리 서비스
상품(product)와 주문(order) 도메인으로 구성된 간단한 프로젝트입니다.

## 개발환경
 - Language : JAVA 17
 - Framework : Spring Boot
 - Database : MySQL

## Pagination
  ### Pageable을 적용하여 요청URL의 파라미터를 통해 페이지조회가 가능하도록 하였습니다.

```
{
    "content": [
        {
            "orderId": 11,
            "productId": 4,
            "productName": "Product 5",
            "quantity": 1
        },
        {
            "orderId": 12,
            "productId": 4,
            "productName": "Product 5",
            "quantity": 1
        },
        {
            "orderId": 13,
            "productId": 4,
            "productName": "Product 5",
            "quantity": 1
        },
        {
            "orderId": 14,
            "productId": 5,
            "productName": "Product 5",
            "quantity": 1
        }
    ],
    "empty": false,
    "first": false,
    "last": true,
    "number": 2,
    "numberOfElements": 4,
    "pageable": {
        "offset": 10,
        "pageNumber": 2,
        "pageSize": 5,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 5,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 14,
    "totalPages": 3
}
```

### N+1 문제
페이지를 가져오는 ooo에 @EntityGraph(attributePaths = "product")를 추가하여 N+1 문제를 해결하였습니다.
해결 전
```
Hibernate: //orders를 페이지로 조회
    /* <criteria> */ select
        o1_0.order_id,
        o1_0.product_id,
        o1_0.quantity 
    from
        orders o1_0 
    order by
        o1_0.order_id 
    limit
        ?, ?
        
// 추가로 쿼리가 나가는 N+1문제 발생
Hibernate: 
    select
        p1_0.id,
        p1_0.price,
        p1_0.product_name,
        p1_0.stock 
    from
        products p1_0 
    where
        p1_0.id=?
Hibernate: 
    select
        p1_0.id,
        p1_0.price,
        p1_0.product_name,
        p1_0.stock 
    from
        products p1_0 
    where
        p1_0.id=?
```

해결 후
```
Hibernate: 
    /* <criteria> */ select
        o1_0.order_id,
        p1_0.id,
        p1_0.price,
        p1_0.product_name,
        p1_0.stock,
        o1_0.quantity 
    from
        orders o1_0 
    left join 
        products p1_0 
            on p1_0.id=o1_0.product_id 
    order by
        o1_0.order_id 
    limit
        ?, ?
```

## 재고차감
상품조회시 @Lock(LockModeType.PESSIMISTIC_WRITE)를 사용하여 동시에 같은 상품의 재고에 접근하지 못하도록 하였습니다.

  
