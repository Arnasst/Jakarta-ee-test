package lt.vu.mybatis.model;

public class Competition {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.COMPETITION.ID
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.COMPETITION.PLACE
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    private String place;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.COMPETITION.ID
     *
     * @return the value of PUBLIC.COMPETITION.ID
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.COMPETITION.ID
     *
     * @param id the value for PUBLIC.COMPETITION.ID
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.COMPETITION.PLACE
     *
     * @return the value of PUBLIC.COMPETITION.PLACE
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    public String getPlace() {
        return place;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.COMPETITION.PLACE
     *
     * @param place the value for PUBLIC.COMPETITION.PLACE
     *
     * @mbg.generated Mon Mar 27 17:18:45 EEST 2023
     */
    public void setPlace(String place) {
        this.place = place;
    }
}