import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    public void restaurant_adding_for_testing() {
        restaurant = service.addRestaurant("Amelie's cafe", "Mumbai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        service.addRestaurant("paris cafe", "Dadar", LocalTime.parse("09:30:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup", 150);
        restaurant.addToMenu("Vegetable lasagne", 300);

    }
        //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        @Test
        public void searching_for_existing_restaurant_should_return_expected_restaurant_object () throws
        restaurantNotFoundException {
            //WRITE UNIT TEST CASE HERE
            restaurant_adding_for_testing();
            assertEquals("Amelie's cafe", service.findRestaurantByName("Amelie's cafe").getName());
        }


        @Test
        public void searching_for_non_existing_restaurant_should_throw_exception () throws restaurantNotFoundException {
            //WRITE UNIT TEST CASE HERE
            restaurant_adding_for_testing();
            assertThrows(restaurantNotFoundException.class, () -> {
                service.findRestaurantByName("Amelia");
            });
        }
        //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


        //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        @Test
        public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1 () throws restaurantNotFoundException
        {
            LocalTime openingTime = LocalTime.parse("10:30:00");
            LocalTime closingTime = LocalTime.parse("22:00:00");
            restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
            restaurant.addToMenu("Sweet corn soup", 150);
            restaurant.addToMenu("Vegetable lasagne", 300);

            int initialNumberOfRestaurants = service.getRestaurants().size();
            service.removeRestaurant("Amelie's cafe");
            assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
        }

        @Test
        public void removing_restaurant_that_does_not_exist_should_throw_exception () throws restaurantNotFoundException
        {
            LocalTime openingTime = LocalTime.parse("10:30:00");
            LocalTime closingTime = LocalTime.parse("22:00:00");
            restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
            restaurant.addToMenu("Sweet corn soup", 150);
            restaurant.addToMenu("Vegetable lasagne", 300);

            assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
        }

        @Test
        public void add_restaurant_should_increase_list_of_restaurants_size_by_1 () {
            LocalTime openingTime = LocalTime.parse("10:30:00");
            LocalTime closingTime = LocalTime.parse("22:00:00");
            restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
            restaurant.addToMenu("Sweet corn soup", 150);
            restaurant.addToMenu("Vegetable lasagne", 300);

            int initialNumberOfRestaurants = service.getRestaurants().size();
            service.addRestaurant("Pumpkin Tales", "Mumbai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
            assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
        }
        //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
    }
