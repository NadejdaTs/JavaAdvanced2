import com.sun.source.tree.Tree;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private static final String LABEL = "test";
    private static final double PRICE = 9.36;
    private static final int QUANTITY = 1;
    private ProductStock stock;
    private Product defaultProduct;

    @Before
    public void setUp(){
        this.stock = new Instock();
        this.defaultProduct = new Product(LABEL, PRICE, QUANTITY);
    }

    @Test
    public void testAddShouldAddTheProductInsideThesStock(){
        stock.add(defaultProduct);
        assertTrue(stock.contains(defaultProduct));
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductNotPresentAndThenTrueAfterAdded(){
        assertFalse(stock.contains(defaultProduct));
        stock.add(defaultProduct);
        assertTrue(stock.contains(defaultProduct));
    }

    @Test
    public void testAddShouldNotAddTheSameProductASecondTime(){
        stock.add(defaultProduct);
        stock.add(defaultProduct);
        assertEquals(1, stock.getCount());
    }

    @Test
    public void testCountShoudReturnTheCorrectNumberOfProducts(){
        //Assert zero when empty
        assertEquals(0, stock.getCount());
        stock.add(defaultProduct);
        assertEquals(1, stock.getCount());
    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenOnlyOnePresent(){
        stock.add(defaultProduct);
        Product product = stock.find(0);
        assertNotNull(product);
        assertEquals(defaultProduct.label, product.label);
    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenTheProductIsInBetweenOtherProducts(){
        stubProducts();

        stock.add(defaultProduct);
        Product product = stock.find(4);
        assertNotNull(product);
        assertEquals("test_Label_5", product.label);
    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenTheProductIsLast(){
        stubProducts();

        stock.add(defaultProduct);
        Product product = stock.find(9);
        assertNotNull(product);
        assertEquals("test_Label_10", product.label);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexWhenIndexOutOfBounds(){
        stubProducts();
        stock.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexWhenIndexOutOfBoundsWhenStockIsEmpty(){
        stock.find(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexWhenIndexOutOfBoundsNegative(){
        stubProducts();
        stock.find(-1);
    }

    @Test
    public void testChangeQuantityShouldUpdateTheCorrProductWithTheCorrAmount(){
        stubProducts();
        stock.add(defaultProduct);
        assertEquals(QUANTITY, defaultProduct.quantity);

        stock.changeQuantity(defaultProduct.label, 13);
        assertEquals(13, defaultProduct.quantity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailIfProductIsNotPreset(){
        stubProducts();
        stock.changeQuantity(defaultProduct.label, 13);
    }

    @Test
    public void testFindByLabelShouldReturnTheCorrProd(){
        stubProducts();
        stock.add(defaultProduct);
        Product byLabel = stock.findByLabel(defaultProduct.label);
        assertNotNull(byLabel);
        assertEquals(defaultProduct.label, byLabel.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldFailWhenProductWithSuchLabelIsNotPresent(){
        stubProducts();
        stock.findByLabel(defaultProduct.label);
    }

    @Test
    public void testFindFirstByAlphabeticalOrderReturnEmptyCollectionWhenStockIsZero(){
        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(0);
        List<Product> list = createListFromIterable(iterable);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderReturnEmptyCollectionWhenParameterIsTooLarge(){
        stubProducts();
        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(11);
        List<Product> list = createListFromIterable(iterable);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderReturnTheCorrectNumberOfProducts(){
        stubProducts();
        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(8);
        List<Product> list = createListFromIterable(iterable);
        assertEquals(8, list.size());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderReturnTheObjectsSorted(){
        stubProducts();
        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(8);
        List<Product> list = createListFromIterable(iterable);
        /*Set<String> expectedLabels = new TreeSet<>(list.stream()
                .map(Product::getLabel)
                .collect(Collectors.toList()));*/
        Set<String> expectedLabels = list.stream()
                .map(Product::getLabel)
                .collect(Collectors.toCollection(TreeSet::new));

        int i = 0;
        for (String expectedLabel : expectedLabels) {
            assertEquals(expectedLabel, list.get(i++).getLabel());
        }
    }

    @Test
    public void testFindAllInRangeShouldReturnEmptyCollectionIfNoSuchRangePresent(){
        stubProducts();
        List<Product> product = createListFromIterable(stock.findAllInRange(55555, 55555555));
        assertTrue(product.isEmpty());
    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange(){
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllInRange(20.00, 30.00));
        assertEquals(3, products.size());

        for (Product product : products) {
            assertTrue(product.getPrice() > 20.00 && product.getPrice() <= 30.00);
        }
    }

    @Test
    public void testFindAllInRangeShouldReturnProductsOrderedByPriceDescending(){
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllInRange(20.00, 30.00));

        List<Product> expected = products.stream()
                .sorted((f, s) -> Double.compare(s.getPrice(), f.getPrice()))
                .collect(Collectors.toList());

        assertEquals(expected, products);
    }

    @Test
    public void testFindAllByPriceShouldReturnTheCorrectPriceedObject(){
        stubProducts();
        stock.add(defaultProduct);
        List<Product> products = createListFromIterable(stock.findAllByPrice(10.24));
        assertEquals(3, products.size());

        for (Product product : products) {
            assertEquals(10.24, product.price, 0.00);
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionIfNoSuchPricedObjectsExist(){
        stubProducts();
        List<Product> product = createListFromIterable(stock.findAllByPrice(55.55));
        assertTrue(product.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldFailIfTheCountIsGreaterThanTheTotalNumberOfProducts(){
        stubProducts();
        stock.findFirstMostExpensiveProducts(stock.getCount() + 1);
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectItem(){
        List<Product> expected = stubProducts().stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());

        List<Product> products = createListFromIterable(stock.findFirstMostExpensiveProducts(3));
        assertEquals(expected, products);
        //stock.findFirstMostExpensiveProducts(stock.getCount() + 1);
    }

    @Test
    public void testFindAllByQuantityShouldReturnAnEmptyCollectionIfNoProductHasSuchQuantity(){
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllByQuantity(100));
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindAllByQuantityShouldOnlyProductsWithTheMatchingQuantity(){
        stubProducts();
        List<Product> products = createListFromIterable(stock.findAllByQuantity(5));
        assertEquals(4, products.size());
        for (Product product : products) {
            assertEquals(5, product.getQuantity());
        }
    }

    @Test
    public void testIteratorShouldReturnAllProductInStock(){
        List<Product> expected = createListFromIterable(stubProducts());

        Iterator<Product> iterator = stock.iterator();
        assertNotNull(iterator);

        int index = 0;
        while(iterator.hasNext()){
            Product product = iterator.next();
            assertEquals(expected.get(index++).getLabel(), (product.getLabel()));
        }
    }

    private List<Product> createListFromIterable(Iterable<Product> iterable){
        assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }

    private List<Product> stubProducts() {
        List<Product> products = Arrays.asList(
                new Product("test_Label_1", 10.24, 2),
                new Product("test_Label_2", 10.24, 5),
                new Product("test_Label_3", 10.24, 2),
                new Product("test_Label_4", 18.00, 2),
                new Product("test_Label_5", 20.00, 5),
                new Product("test_Label_6", 20.24, 2),
                new Product("test_Label_7", 14.00, 2),
                new Product("test_Label_8", 18.00, 5),
                new Product("test_Label_9", 21.24, 2),
                new Product("test_Label_10", 30.00, 5)
        );

        for (Product product : products) {
            stock.add(product);
        }

        return products;
        /*stock.add(new Product("test_Label_1", 10.24, 2));
        stock.add(new Product("test_Label_2", 10.24, 2));
        stock.add(new Product("test_Label_3", 10.24, 2));
        stock.add(new Product("test_Label_4", 18.24, 2));
        stock.add(new Product("test_Label_5", 20.00, 2));
        stock.add(new Product("test_Label_6", 20.24, 2));
        stock.add(new Product("test_Label_7", 14.00, 2));
        stock.add(new Product("test_Label_8", 18.24, 2));
        stock.add(new Product("test_Label_9", 21.24, 2));
        stock.add(new Product("test_Label_10", 30.00, 2));*/
    }

}