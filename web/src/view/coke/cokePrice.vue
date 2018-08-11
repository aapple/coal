<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">焦化厂名称</Col>
      <Col>
        <Input v-model="factoryName" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">兰炭种类</Col>
      <Col>
        <Select v-model="productType" style="width:200px">
          <Option value="大料">大料</Option>
          <Option value="中料">中料</Option>
          <Option value="小料">小料</Option>
          <Option value="焦面">焦面</Option>
        </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">是否生产</Col>
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
    <Row>
      <Col span="2" class="ivu_title">含硫</Col>
      <Col>
      <Input v-model="coke_hanliu" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">固定碳</Col>
      <Col>
      <Input v-model="coke_gudingtan" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">挥发份</Col>
      <Col>
      <Input v-model="coke_huifafen" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">灰分</Col>
      <Col>
      <Input v-model="coke_huifen" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">全水</Col>
      <Col>
      <Input v-model="coke_quanshui" clearable style="width: 200px"></Input>
      </Col>
    </Row>

    <br>
    <br>
    <Row>
      <Button type="primary" @click="updatePrice">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateCoalPrice } from '@/api/coal'
export default {
  name: 'cokePrice_page',
  components: {

  },
  data () {
    return {
      factoryName: this.$route.params.factoryName,
      factoryType: '兰炭',
      productType: this.$route.params.productType,
      state: this.$route.params.state,
      price: this.$route.params.price,
      coke_hanliu: this.$route.params.coke_hanliu,
      coke_gudingtan: this.$route.params.coke_gudingtan,
      coke_huifafen: this.$route.params.coke_huifafen,
      coke_huifen: this.$route.params.coke_huifen,
      coke_quanshui: this.$route.params.coke_quanshui
    }
  },
  methods: {

    updatePrice () {
      var data = {
        factoryName: this.factoryName,
        factoryType: this.factoryType,
        productType: this.productType,
        state: this.state,
        price: this.price,
        coke_hanliu: this.coke_hanliu,
        coke_gudingtan: this.coke_gudingtan,
        coke_huifafen: this.coke_huifafen,
        coke_huifen: this.coke_huifen,
        coke_quanshui: this.coke_quanshui
      };
      updateCoalPrice(data).then(res => {
        this.$Message.info("更新成功！");
        this.$router.push({
          name: 'cokePriceList_page'
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
