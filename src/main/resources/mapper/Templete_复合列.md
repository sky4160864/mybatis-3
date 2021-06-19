```xml

<resultMap id="findCountryCityAddressMap" type="map">
    <result property="country" column="country"/>
    <collection property="cityList"
                column="{cityId=city_id,adr=addressCol, dis=districtCol}" //adr作为第二个sql查询条件key,即prop1属性
                ofType="map"                                              //addressCol即为虚拟列名
                javaType="java.util.List" select="selectAddressByCityId"/>
</resultMap>

<resultMap id="selectAddressByCityIdMap" type="map">
    <result property="city" column="city"/>
    <collection property="addressList" column="city" ofType="map" javaType="java.util.List">
        <result property="address" column="address"/>
        <result property="district" column="district"/>
    </collection>
</resultMap>

<select id="findCountryCityAddress" resultMap="findCountryCityAddressMap">
    SELECT
        ct.country,
        ci.city_id,
        IFNULL(#{addressQuery},'') addressCol, //为传入查询条件,构造虚拟列，虚拟列为查询条件参数值
        IFNULL(#{districtQuery},'') districtCol
    FROM
        country ct
    LEFT JOIN city ci ON ct.country_id = ci.country_id
    ORDER BY ct.country_id
</select>

<select id="selectAddressByCityId" parameterType="java.util.Map" resultMap="selectAddressByCityIdMap">
    SELECT
        ci.city,
        ads.address,
      ads.district
    FROM
        (
            SELECT
                city,
                city_id
            FROM
                city ci
            WHERE
                ci.city_id = #{cityId}
        ) ci
    LEFT JOIN address ads ON ads.city_id = ci.city_id
    <where>
        <if test="adr!=null and adr!=''">
            and ads.address RegExp #{adr}
        </if>
        <if test="dis!=null and dis!=''">
            ads.district Regexp #{dis}
        </if>
    </where>

</select>


```
