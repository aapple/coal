<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">厂矿名称</Col>
      <Col>
        <Input v-model="factoryName" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">厂矿类型</Col>
      <Col>
      <Select v-model="factoryType" style="width:200px">
        <Option value="煤炭" >煤炭</Option>
        <Option value="兰炭" >兰炭</Option>
        <Option value="物流" >物流</Option>
      </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">管理用户名</Col>
      <Col>
      <Input v-model="userName" clearable style="width: 200px"></Input>
      </Col>
    </Row>

    <br>
    <Row>
      <Col span="2" class="ivu_title">业务员名字</Col>
      <Col>
      <Input v-model="sallerName" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">业务员联系电话</Col>
      <Col>
      <Input v-model="sallerPhone" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>

    <br>
    <br>
    <Row>
      <Button type="primary" @click="updateCoalPrice">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateFactory } from '@/api/coal'
export default {
  name: 'factory_page',
  components: {

  },
  data () {
    return {
      factoryName: this.$route.params.factoryName,
      factoryType: this.$route.params.factoryType,
      userName: this.$route.params.userName,
      sallerName: this.$route.params.sallerName,
      sallerPhone: this.$route.params.sallerPhone
    }
  },
  methods: {

    updateCoalPrice () {
      var data = {
        factoryName: this.factoryName,
        factoryType: this.factoryType,
        userName: this.userName,
        sallerName: this.sallerName,
        sallerPhone: this.sallerPhone
      };
      updateFactory(data).then(res => {
        this.$Message.info("更新成功！");
        this.$router.push({
          name: 'factoryList_page'
        })
        resolve()
      }).catch(err => {

      })
    }
  }
}
</script>

<style lang="less">
  .ivu_title {
    padding-top: 5px;
  }
</style>
