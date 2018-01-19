package entities

import com.fasterxml.jackson.databind.ObjectMapper

class Customer {


    private String id

    private String first_name

    private String last_name

    private Properties properties = new Properties()

    static String getCustomerJSONString(Customer customer) {
        ObjectMapper mapper = new ObjectMapper()
        String pojoJSON = mapper.writeValueAsString(customer)
        return pojoJSON
    }

     String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getFirst_name() {
        return first_name
    }

    void setFirst_name(String first_name) {
        this.first_name = first_name
    }

    String getLast_name() {
        return last_name
    }

    void setLast_name(String last_name) {
        this.last_name = last_name
    }

    Properties getProperties() {
        return properties
    }

    void setProperties(Properties properties) {
        this.properties = properties
    }

    @Override
    String toString() {
        return "{id = " + id + ", first_name = " + first_name + ", last_name = " + last_name + ", properties = " + properties + "}"
    }

}
