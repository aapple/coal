<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">物流部名称</Col>
      <Col>
      <Select :disabled='factoryName != undefined' v-model="factoryName" style="width:200px">
        <Option v-for="item in factoryNames" :value="item" :key="item">{{ item }}</Option>
      </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">出发地</Col>
      <Col>
      <Input v-model="start" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">目的地</Col>
      <Col>
      <Input v-model="end" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">用车数量</Col>
      <Col>
      <Input v-model="count" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">是否有效</Col>
      <Col>
      <Select v-model="state" style="width:200px">
        <Option :value="1" >是</Option>
        <Option :value="0" >否</Option>
      </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">价格</Col>
      <Col>
      <Input v-model="price" clearable style="width: 200px"></Input>
      </Col>
    </Row>

    <br>
    <br>
    <Row>
      <Button type="primary" @click="updateLogistics">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateLogistics } from '@/api/coal'
export default {
  name: 'logistics_page',
  components: {

  },
  data () {
    return {
      logisticsId: this.$route.params.logisticsId,
      factoryName: this.$route.params.factoryName,
      factoryNames: this.$route.params.factoryNames,
      start: this.$route.params.start,
      end: this.$route.params.end,
      count: this.$route.params.count,
      state: this.$route.params.state,
      price: this.$route.params.price
    }
  },
  methods: {
    updateLogistics () {
      var data = {
        logisticsId: this.logisticsId,
        factoryName: this.factoryName,
        start: this.start,
        end: this.end,
        count: this.count,
        state: this.state,
        price: this.price
      };
      updateLogistics(data).then(res => {
        this.$Message.info("更新成功！");
        this.$router.push({
          name: 'logisticsList_page'
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
