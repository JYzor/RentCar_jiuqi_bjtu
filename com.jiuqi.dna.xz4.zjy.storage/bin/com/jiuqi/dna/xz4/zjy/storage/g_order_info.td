<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="g_order_info" ID="072C29BC1C7DEDCD08219A3F062E5331" name-db="g_order_info" category="" title="" author="" description="" packagename="com.jiuqi.dna.xz4.zjy.storage" name="g_order_info" isneedrebuild="false">
	<fields>
		<field dbtable="g_order_info" name="ORDER_NUM" name-db="ORDER_NUM" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="订单编号" description="" ID="49CB454DCDA22FBD37B65CCAE9A2FBA1"/>
		<field dbtable="g_order_info" name="PLATE_NUM" name-db="PLATE_NUM" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="车牌号" description="" ID="3AFF6D2825DA6CBD6741FAE3F6D1B5FA"/>
		<field dbtable="g_order_info" name="RENT_USER" name-db="RENT_USER" type="guid" primary-key="false" keep-valid="false" author="" title="租用人ID" description="" ID="7D7B3877DD6BE7D64C90931E94970983"/>
		<field dbtable="g_order_info" name="START_TIME" name-db="START_TIME" type="date" primary-key="false" keep-valid="false" author="" title="开始时间" description="" ID="EF10093E4A4BDEB4510BA235B2472769"/>
		<field dbtable="g_order_info" name="END_TIME" name-db="END_TIME" type="date" primary-key="false" keep-valid="false" author="" title="结束时间" description="" ID="0BF7AB082F9DF750A89E40F33FDB0769"/>
		<field dbtable="g_order_info" name="ORDER_STATE" name-db="ORDER_STATE" type="int" primary-key="false" keep-valid="false" author="" title="订单状态" description="" ID="F1E9B73F097CCD70FF94D8AB7BFB8BF7"/>
		<field dbtable="g_order_info" name="RENT" name-db="RENT" type="double" primary-key="false" keep-valid="false" author="" title="租金" description="" ID="13EF3E31D3C7E25E50902A77CC752E97"/>
	</fields>
	<indexs/>
	<hierarchies/>
	<dbtables>
		<dbtable author="" name="g_order_info" name-db="g_order_info" title="" description=""/>
	</dbtables>
	<relations/>
</tabledefine>
