/*
Yusuf Akyuz
21050111054
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;
import java.util.NoSuchElementException;

public class DoublyLinkedList {
    private Country head;
    private Country tail;
    private int length;

    private static class Country {
        private String country;
        private String population;
        private String capitalCity;
        private String largestCity;
        private String officialCity;
        private String currency;
        private Country next;
        private Country previous;

        public Country(String country, String population, String capitalCity, String largestCity, String officialCity, String currency) {
            this.country = country;
            this.population = population;
            this.capitalCity = capitalCity;
            this.largestCity = largestCity;
            this.officialCity = officialCity;
            this.currency = currency;
        }

        public void displayConuntry() {
            System.out.println(country + " " + population + " " + capitalCity + " " + largestCity + " " + officialCity + " " + currency);
        }
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int length() {
        return length;
    }

    public void insertLast(Country obj) {
        if (isEmpty()) {
            head = obj;
            tail = obj;
        } else {
            tail.next = obj;
            obj.previous = tail;
            tail = obj;
        }
        length++;
    }

    public void insertFirst(Country obj) {
        if (isEmpty()) {
            head = obj;
            tail = obj;
        } else {
            obj.next = head;
            head.previous = obj;
            head = obj;
        }
        System.out.println("A new country has been added.");
        length++;
    }

    public void delete(String country) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Country temp = head;
        while (temp != null) {
            if (temp.country.equalsIgnoreCase(country)) {
                // Remove the current node
                if (temp == head) {
                    head = temp.next;
                } else if (temp == tail) {
                    tail.previous.next = null;
                    tail = temp.previous;

                } else {
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                }
                System.out.print("Removes the country " + country + " from the list");
                break; // Stop iterating after removing the first match
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public void display() {
        if (head == null) {
            return;
        }
        Country temp = head;
        while (temp != null) {
            temp.displayConuntry();
            temp = temp.next;
        }
    }

    public void compare(String compareElement, String value, String operator) {
        if (head == null) {
            return;
        }

        DoublyLinkedList linkedList = new DoublyLinkedList();
        Country temp = head;

        while (temp != null) {
            switch (compareElement) {
                case "population":
                    long tempPop = Long.parseLong(temp.population.replace(".", ""));
                    long valuePar = Long.parseLong(value.replace(".", ""));
                    if (tempPop > valuePar && operator.equalsIgnoreCase(">")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (tempPop < valuePar && operator.equalsIgnoreCase("<")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (tempPop == valuePar && operator.equalsIgnoreCase("=")) {
                        linkedList.insertLast(createCountry(temp));
                    }
                    break;
                case "country":
                    if (temp.country.compareTo(value) > 0 && operator.equals(">")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.country.compareTo(value) < 0 && operator.equals("<")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.country.toLowerCase().compareTo(value.toLowerCase()) == 0 && operator.equals("=")) {
                        linkedList.insertLast(createCountry(temp));
                    }
                    break;
                case "capital_city":
                    if (temp.capitalCity.compareTo(value) > 0 && operator.equals(">")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.capitalCity.compareTo(value) < 0 && operator.equals("<")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.largestCity.equalsIgnoreCase(value) && operator.equals("=")) {
                        linkedList.insertLast(createCountry(temp));
                    }
                    break;
                case "largest_city":
                    if (temp.largestCity.compareTo(value) > 0 && operator.equals(">")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.largestCity.compareTo(value) < 0 && operator.equals("<")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.largestCity.equalsIgnoreCase(value) && operator.equals("=")) {
                        linkedList.insertLast(createCountry(temp));
                    }
                    break;
                case "currency":
                    if (temp.currency.compareTo(value) > 0 && operator.equals(">")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.currency.compareTo(value) < 0 && operator.equals("<")) {
                        linkedList.insertLast(createCountry(temp));
                    } else if (temp.currency.equalsIgnoreCase(value) && operator.equals("=")) {
                        linkedList.insertLast(createCountry(temp));
                    }
                    break;
                default:
                    System.out.println("Wrong Statement!");
            }
            temp = temp.next;
        }

        linkedList.display();
    }

    private Country createCountry(Country temp) {
        return new Country(temp.country, temp.population, temp.capitalCity, temp.largestCity, temp.officialCity, temp.currency);
    }

    public static void main(String[] args) {

        DoublyLinkedList dll = new DoublyLinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String countryInfo;
            while ((countryInfo = br.readLine()) != null) {
                String[] countryLineArr = countryInfo.split(" ");
                if (countryLineArr.length == 6) {
                    String country = countryLineArr[0];
                    String population = countryLineArr[1];
                    String capitalCity = countryLineArr[2];
                    String largestCity = countryLineArr[3];
                    String officialCity = countryLineArr[4];
                    String currency = countryLineArr[5];
                    Country newCountry = new Country(country, population, capitalCity, largestCity, officialCity, currency);
                    dll.insertLast(newCountry);
                }else {
                    System.out.println("Invalid Input Type!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("query.txt"))) {
            String queryInfo;
            while ((queryInfo = br.readLine()) != null) {
                String[] queryLineArr = queryInfo.split(" ");

                if (queryLineArr.length == 1) {
                    System.out.println("Invalid query line!");
                }else{
                    if (queryLineArr[0].equalsIgnoreCase("add")) {
                        if (queryLineArr.length == 7) {
                            String name = queryLineArr[1];
                            String population = queryLineArr[2];
                            String capitalCity = queryLineArr[3];
                            String largestCity = queryLineArr[4];
                            String officialCity = queryLineArr[5];
                            String currency = queryLineArr[6];
                            dll.insertFirst(new Country(name, population, capitalCity, largestCity, officialCity, currency));
                        } else {
                            System.out.println("The input structure is wrong!");
                        }
                    } else if (queryLineArr[0].equalsIgnoreCase("delete")) {
                        String name = queryLineArr[1];
                        dll.delete(name);
                    } else if (queryLineArr[0].equalsIgnoreCase("query") && queryLineArr[1].equalsIgnoreCase("print_all")) {
                        dll.display();
                    } else if (queryLineArr[2].equals("<") || queryLineArr[2].equals(">") || queryLineArr[2].equals("=")) {
                        dll.compare(queryLineArr[1], queryLineArr[3], queryLineArr[2]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
