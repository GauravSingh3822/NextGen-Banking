package com.banking.backend.Banking_Backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BankingBackendApplication {

//	@Autowired
//	private StudentRepo studentRepo;
//
//    @Autowired
//    private ProductRepo productRepo;
//
//    @Autowired
//    private CategoryRepo categoryRepo;

	private static final Logger logger = LoggerFactory.getLogger(BankingBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankingBackendApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
////=======================================================================================
//        // Create a new student object
//        Student student = new Student();
//        student.setStudentName("Manu");
//        student.setAbout("I am a software engineer");
//        student.setStudentId(14);
////=======================================================================================
//        // Create a new laptop object
//        Laptop laptop = new Laptop();
//        laptop.setModelNumber("Dell XPS 15");
//        laptop.setBrand("Dell");
//        laptop.setLaptopId(1231);
////=======================================================================================
//        // Set the laptop for the student
//        laptop.setStudent(student);
//        student.setLaptop(laptop);
////=======================================================================================
//        // Save the student to the database
//        Student savedStudent = studentRepo.save(student);
//        logger.info("Student saved: {}", savedStudent);
////=======================================================================================
//        // Fetch the saved student from the database
//        Student student1 = studentRepo.findById(14).get();
//        logger.info("Student fetched: {}", student1.getStudentName());
////=======================================================================================
//        // Fetch the laptop associated with the student
//        Laptop laptop1 = student1.getLaptop();
//        logger.info("Laptop fetched: {}, {}", laptop1.getBrand(), laptop1.getModelNumber());
////=======================================================================================
//        // One to Many Relationship
//        Student student2 = new Student();
//        student2.setStudentName("Ramesh");
//        student2.setAbout("I am a software engineer");
//        student2.setStudentId(15);
////=======================================================================================
//        Address a1 = new Address();
//        a1.setAddressId(131);
//        a1.setStreet("B-123");
//        a1.setCity("Mumbai");
//        a1.setState("Maharashtra");
//        a1.setCountry("India");
//        a1.setStudent(student2);
////=======================================================================================
//        Address a2 = new Address();
//        a2.setAddressId(132);
//        a2.setStreet("B-12356");
//        a2.setCity("Lucknow");
//        a2.setState("Uttar Pradesh");
//        a2.setCountry("India");
//        a2.setStudent(student2);
////=======================================================================================
//        // Add addresses to the student
//        List<Address> addressList = new ArrayList<>();
//        addressList.add(a1);
//        addressList.add(a2);
//        student2.setAddresses(addressList);
////=======================================================================================
//            Products product1 = new Products();
//            product1.setpId("P101");
//            product1.setProductName("Apple Macbook Pro");
//
//            Products product2 = new Products();
//            product2.setpId("P102");
//            product2.setProductName("Lenovo IdeaPad");
//
//            Products product3 = new Products();
//            product3.setpId("P103");
//            product3.setProductName("HP Pavilion");
//
////=======================================================================================
//            Category category1 = new Category();
//            category1.setcId("C101");
//            category1.setTitle("Electronics");
//
//            Category category2 = new Category();
//            category2.setcId("C102");
//            category2.setTitle("Electronics");
//
////=======================================================================================
//            List<Products> category1Products = category1.getProducts();
//            category1Products.add(product1);
//            category1Products.add(product2);
//            category1Products.add(product3);
//
//
//            List<Products> category2Products = category2.getProducts();
//            category2Products.add(product1);
//            category2Products.add(product2);
//
//
////=======================================================================================
//        categoryRepo.save(category1);
//        categoryRepo.save(category2);
//
////=======================================================================================
//            Category category = categoryRepo.findById("C101").get();
//            System.out.println(category.getProducts().size());
//
////=======================================================================================
//        // Save the student with addresses to the database
//        Student savedStudent2 = studentRepo.save(student2);
//        logger.info("Student saved with addresses: {}", savedStudent2);
//    }
}