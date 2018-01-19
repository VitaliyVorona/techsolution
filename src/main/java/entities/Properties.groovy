package entities

class Properties {

        private String age

        private String date_of_birth

        private String active

        String getAge ()
        {
            return age
        }

        void setAge (String age)
        {
            this.age = age
        }

        String getDate_of_birth ()
        {
            return date_of_birth
        }

        void setDate_of_birth (String date_of_birth)
        {
            this.date_of_birth = date_of_birth
        }

        String getActive ()
        {
            return active
        }

        void setActive (String active)
        {
            this.active = active
        }

        @Override
        String toString()
        {
            return "{age = "+age+", date_of_birth = "+date_of_birth+", active = "+active+"}"
        }
    }