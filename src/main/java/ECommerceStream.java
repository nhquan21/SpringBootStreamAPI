import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class User {
    int id;
    String name;
    String email;
    String city;

    public User(int id, String name, String email, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s", id, name, city);
    }
}

class Product {
    int id;
    String name;
    String category;
    double price;

    public Product(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - $%.2f", id, name, category, price);
    }
}

class Order {
    int id;
    int userId;
    LocalDate orderDate;
    double totalAmount;

    public Order(int id, int userId, LocalDate orderDate, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return String.format("Ord[%d] User[%d] - $%.2f", id, userId, totalAmount);
    }
}

class OrderDetail {
    int detailId;
    int orderId;
    int productId;
    int quantity;

    public OrderDetail(int detailId, int orderId, int productId, int quantity) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}

public class ECommerceStream {
    public static void main(String[] args) {
        // 1. Users
        List<User> users = Arrays.asList(
                new User(1, "Nguyen Van A", "ana@gmail.com", "Hanoi"),
                new User(2, "Tran Thi B", "btran@yahoo.com", "Hcm"),
                new User(3, "Le Van C", "cle@gmail.com", "Danang"),
                new User(4, "Pham Thi D", "dpham@outlook.com", "Hanoi"),
                new User(5, "Hoang Van E", "ehoang@gmail.com", "Hcm"),
                new User(6, "Doan Van F", "fdoan@gmail.com", "Hanoi"),
                new User(7, "Vu Thi G", "gvu@gmail.com", "Danang"),
                new User(8, "Nguyen Van H", "hnguyen@yahoo.com", "Hcm")
        );

        // 2. Products
        List<Product> products = Arrays.asList(
                new Product(101, "iPhone 15", "Electronics", 1000.0),
                new Product(102, "Samsung S24", "Electronics", 950.0),
                new Product(103, "Ao Thun", "Fashion", 15.0),
                new Product(104, "Giay Nike", "Fashion", 120.0),
                new Product(105, "Tu Lanh", "Appliances", 500.0),
                new Product(106, "Tai nghe Sony", "Electronics", 150.0),
                new Product(107, "Laptop Dell", "Electronics", 1200.0),
                new Product(108, "Quan Jean", "Fashion", 40.0)
        );

        // 3. Orders
        List<Order> orders = Arrays.asList(
                new Order(1001, 1, LocalDate.of(2023, 10, 1), 1015.0),
                new Order(1002, 2, LocalDate.of(2023, 10, 5), 120.0),
                new Order(1003, 1, LocalDate.of(2023, 11, 10), 950.0),
                new Order(1004, 3, LocalDate.of(2023, 11, 12), 500.0),
                new Order(1005, 2, LocalDate.of(2023, 11, 15), 55.0),
                new Order(1006, 4, LocalDate.of(2023, 11, 20), 1200.0),
                new Order(1007, 1, LocalDate.of(2023, 12, 1), 40.0),
                new Order(1008, 5, LocalDate.of(2023, 12, 5), 1240.0),
                new Order(1009, 2, LocalDate.of(2023, 12, 10), 150.0)
        );

        // 4. OrderDetails
        List<OrderDetail> orderDetails = Arrays.asList(
                new OrderDetail(1, 1001, 101, 1), new OrderDetail(2, 1001, 103, 1),
                new OrderDetail(3, 1002, 104, 1), new OrderDetail(4, 1003, 102, 1),
                new OrderDetail(5, 1004, 105, 1), new OrderDetail(6, 1005, 103, 1),
                new OrderDetail(7, 1005, 108, 1), new OrderDetail(8, 1006, 107, 1),
                new OrderDetail(9, 1007, 108, 1), new OrderDetail(10, 1008, 107, 1),
                new OrderDetail(11, 1008, 108, 1), new OrderDetail(12, 1009, 106, 1)
        );



        // 1. Filter by City: Get a list of all Users who live in "Hanoi".
        // Lấy ra danh sách những ng ở hà nội sử dụng equals để so sánh
        System.out.println("1.----------");
        List<User> filterByCityGetAllUsers = users
                .stream()
                .filter(u -> u.city.equals("Hanoi"))
                .toList();
        filterByCityGetAllUsers.forEach(System.out::println);

        // 2. Filter & Map: Get a list of names of all Products that belong to the "Electronics" category and have a price greater than 900.
        //In ra những san phẩm là điện tử và có giá trị hơn 900
        System.out.println("2.----------");
        List<String> findProductByCategoryAndPrice = products
                .stream()
                .filter(p -> p.price >= 900)
                .filter(p -> p.category.equals("Electronics"))
                .map(Product::getName)
                .toList();

        findProductByCategoryAndPrice.forEach(System.out::println);
        // 3. Sorting: Get a list of Products sorted by price in descending order.
        // In ra sản phẩm từ thấp lên cao sử dụng sorted
        System.out.println("3.----------");
        List<Product> findProductSortedASC = products
                .stream()
                .sorted(Comparator.comparingDouble(p -> p.price))
                .toList();
        findProductSortedASC.forEach(System.out::println);

        // 4. Date Filtering: Get a list of Orders placed in November 2023.
        //Lấy ra tất cả những đơn hàng tháng 11 năm 2023
        System.out.println("4.----------");
        List<Order> list = orders
                .stream()
                .filter(o -> o.orderDate.getYear() == 2023 && o.orderDate.getMonthValue() == 11)
                .toList();
        list.forEach(System.out::println);

        // 5. Total Revenue: Calculate the sum of totalAmount from all Orders.
        //Tổng tất cả các đơn hàng
        System.out.println("5.----------");
        Double calculate = orders
                .stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
        System.out.println(calculate);

        // 6. Count by Category: Count how many Products belong to each category (Output: Map<String, Integer>).
        //Đếm số sản phẩm cho mỗi danh mục
        System.out.println("6.----------");
        Map<String, Long> countByCategory = products
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        countByCategory.forEach((k, v) -> System.out.println(k + ":" + v));

        // 7. Average Price: Calculate the average price of products for each category (Output: Map<String, Double>).
        //Tính giá trung bình của các sản phẩm cho mỗi danh mục
        System.out.println("7.----------");
        Map<String, Double> averagePrice = products
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
        averagePrice.forEach((k, v) -> System.out.println(k + ":" + v));

        // 8. Max Value: Find the Product with the highest price.
        //Tìm Sản phẩm có giá cao nhất
        System.out.println("8.----------");
        Optional<Product> optionalProduct = products
                .stream()
                .max(Comparator.comparingDouble(Product::getPrice));

        optionalProduct.ifPresent(System.out::println);

        // 9. Purchase History: List the unique names of products that user "Nguyen Van A" has purchased.
        System.out.println("9.----------");
        // Lấy ra tất cả những order có có id ng dùng là Nguyen Van A
        Set<Integer> od =  orders.stream().filter(o -> o.userId == users.stream()
                                .filter(u->u.name.equals("Nguyen Van A")).map(User::getId).findFirst().get()).map(Order::getId).collect(Collectors.toSet());
        Set<Integer> listProductIds = orderDetails
                .stream().filter(orderDetail -> od.contains(orderDetail.orderId)).map(OrderDetail::getProductId).collect(Collectors.toSet());
        products.stream().filter(product -> listProductIds.contains(product.getId())).forEach(System.out::println);

        // 10. Revenue by User: Calculate the total amount spent by each user (Output: Map<String, Double> where Key is User Name).
        System.out.println("10.----------");
        //Map UserId sang Name
        Map<Integer,String> userIdMapName = users.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        //In ra Name người dùng và tính tổng số tiền tất cả những sản phẩm đã mua
        Map<String,Double> userTotal = orders
                .stream()
                .collect(Collectors.groupingBy(o -> userIdMapName.get(o.userId), Collectors.summingDouble(o -> o.totalAmount)));

        userTotal.forEach((k,v) -> System.out.println(k + ":" + v));

        // 11. Revenue by City: Calculate the total revenue generated from customers in each city.
        System.out.println("11.----------");
        //Map userId sang City
        Map<Integer,String> userIdToCity = users.stream().collect(Collectors.toMap(User::getId, User::getCity));
        //In ta tổng số doanh thu của mỗi khách hàng của thành phố
        Map<String,Double> calculateCity = orders.
                stream()
                .collect(Collectors.groupingBy(
                        o -> userIdToCity.get(o.userId),
                        Collectors.summingDouble(o -> o.getTotalAmount())
                ));
        calculateCity.forEach((k,v) -> System.out.println(k + ":" + v));

        // 12. Product Sales: Calculate the total quantity sold for each product (Output: Map<String, Integer> where Key is Product Name).
        System.out.println("12.----------");
        // Map Key là OrderId còn value là UserName
        Map<Integer,String> productIdsMapName = products.stream().collect(Collectors.toMap(Product::getId, Product::getName));
        // In ra tổng số dản phẩm đã được bán
        Map<String,Integer> calculateQuantity = orderDetails
               .stream()
               .collect(Collectors.groupingBy(
                       o -> productIdsMapName.get(o.productId),
                       Collectors.summingInt(o -> o.quantity)

               ));
        calculateQuantity.forEach((k,v) -> System.out.println(k + ":" + v));

        // 13. Inactive Users: Find a list of names of users who have never placed any order.
        System.out.println("13.----------");
        //Lấy danh sách UserId trong Orders
        Set<Integer> userIdsInOrder = orders.stream()
                .map(Order::getUserId)
                .collect(Collectors.toSet());
        //In ra những người chưa từng mua hàng
        List<User> p = users
                .stream()
                .filter(u -> !userIdsInOrder.contains(u.id)).toList();

        p.forEach(System.out::println);

        // 14. Unsold Products: Find a list of names of products that have never been sold (not present in any OrderDetail).
        System.out.println("14.----------");
        // Lấy tất cả danh sách productId trong orderDetail
        Set<Integer> productIdsToOrderDetail = orderDetails.stream().
                map(OrderDetail::getProductId)
                .collect(Collectors.toSet());
        // In ra danh sách những product chưa đc từng được mưa
        List<Product> productNoOrders = products.
                stream()
                .filter(product -> !productIdsToOrderDetail.contains(product.id)).toList();
        productNoOrders.forEach(System.out::println);
        // 15. Big Spenders: Find a list of users who have spent more than 1000 in total.
        System.out.println("15.----------");
        //Lấy ra tất cả các UserId có giá trị ớn hơn 1000
        Map<Integer,Double> orderIdsMapUserName = orders.stream().collect(Collectors.groupingBy(order -> order.userId,Collectors.summingDouble(Order::getTotalAmount)));
        users.stream().filter(user -> orderIdsMapUserName.containsKey(user.id)).filter(u -> orderIdsMapUserName.get(u.id) > 1000).forEach(System.out::println);

        // 16. Best Seller: Find the name of the product that has the highest total quantity sold.
        System.out.println("16.----------");
        // Map lấy Product ProductId làm key và số ProductId là value
        Map<Integer,Long> productMax = orderDetails
                .stream()
                .collect(Collectors.groupingBy(OrderDetail::getProductId,Collectors.counting()));
        // Lấy ra ProductId có số mua nhiều nhất
        Integer key = productMax.keySet().stream().max(Comparator.comparingInt(o -> o)).get();
        // In ra Name product
        String pmax = products
                .stream()
                .filter(pm -> pm.id == key)
                .map(Product::getName).findFirst().get();
        System.out.println(pmax);

        // 17. Hanoi Preferences: List the unique names of products bought by users living in "Hanoi".
        System.out.println("17.----------");

        // Lấy danh sách userId những người ở hà nội
        Set<Integer> listUserId = users.stream().filter(use -> use.city.equals("Hanoi")).map(User::getId).collect(Collectors.toSet());

        // Lấy danh sách OrderId có những người ở hà nội
        Set<Integer> listOrderIds = orders
                .stream()
                        .filter(order -> listUserId.contains(order.userId))
                                .map(Order::getId).collect(Collectors.toSet());

        // Map UserId sang Name
        Map<Integer,String> productIdMapN = products.stream().collect(Collectors.toMap(Product::getId, Product::getName));

        //Lấy danh productId trong ordertail ở Hà Nội
        Set<Integer> productIds = orderDetails.stream().filter( orderDetail -> listOrderIds.contains(orderDetail.orderId)).map(OrderDetail::getProductId).collect(Collectors.toSet());

        //Dùng set tranh lập dữ liệu và in ra
        Set<String> productNames = productIds.stream()
                .map(productIdMapN::get)
                .collect(Collectors.toSet());
        productNames.forEach(System.out::println);
    }
}

