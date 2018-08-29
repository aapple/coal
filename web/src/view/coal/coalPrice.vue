<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">煤矿名称</Col>
      <Col>
        <Select :disabled='updateFlag' v-model="factoryName" style="width:200px">
          <Option v-for="item in factoryNames" :value="item" :key="item">{{ item }}</Option>
        </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">煤炭种类</Col>
      <Col>
        <Select :disabled='updateFlag' v-model="productType" style="width:200px" @on-change="productTypeChange">
          <Option value="面煤">面煤</Option>
          <Option value="块煤">块煤</Option>
          <Option value="籽煤">籽煤</Option>
          <Option value="原煤">原煤</Option>
          <Option value="混煤">混煤</Option>
          <Option value="工程煤">工程煤</Option>
        </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">煤炭种类细分</Col>
      <Col>
      <Select :disabled='updateFlag' v-model="productTypeDetail" style="width:200px">
        <Option v-for="item in productTypeDetails" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">是否水洗</Col>
      <Col>
        <Select :disabled='updateFlag' v-model="coalWashing" style="width:200px">
          <Option :value="1" >是</Option>
          <Option :value="0" >否</Option>
        </Select>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">是否过筛</Col>
      <Col>
      <Select :disabled='updateFlag' v-model="graded" style="width:200px">
        <Option :value="1" >是</Option>
        <Option :value="0" >否</Option>
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
      <Col span="2" class="ivu_title">两票价</Col>
      <Col>
      <Input v-model="price" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">一票价</Col>
      <Col>
      <Input v-model="onePrice" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">发热量</Col>
      <Col>
      <Input v-model="coal_fareliang" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">全水分</Col>
      <Col>
      <Input v-model="coal_quanshuifen" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">硫份</Col>
      <Col>
      <Input v-model="coal_liufen" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">挥发份</Col>
      <Col>
      <Input v-model="coal_huifafen" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="2" class="ivu_title">灰分</Col>
      <Col>
      <Input v-model="coal_huifen" clearable style="width: 200px"></Input>
      </Col>
    </Row>

    <br>
    <br>
    <Row>
      <Button type="primary" @click="updateCoalPrice">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateCoalPrice } from '@/api/coal'
export default {
  name: 'coalPrice_page',
  components: {

  },
  data () {
    return {
      updateFlag: this.$route.params.updateFlag,
      factoryName: this.$route.params.factoryName,
      factoryNames: this.$route.params.factoryNames,
      factoryType: '煤炭',
      productType: this.$route.params.productType,
      productTypeDetail: this.$route.params.productTypeDetail,
      productTypeDetails: [],
      coalWashing: this.$route.params.coalWashing,
      graded: this.$route.params.graded,
      state: this.$route.params.state,
      price: this.$route.params.price,
      onePrice: this.$route.params.onePrice,
      coal_fareliang: this.$route.params.coal_fareliang,
      coal_quanshuifen: this.$route.params.coal_quanshuifen,
      coal_huifafen: this.$route.params.coal_huifafen,
      coal_liufen: this.$route.params.coal_liufen,
      coal_huifen: this.$route.params.coal_huifen
    }
  },
  methods: {
    productTypeChange () {
        if (this.productType == "面煤" || this.productType == "工程煤"){
            this.productTypeDetails = [];
        } else if (this.productType == "原煤" || this.productType == "混煤"){
            this.productTypeDetails = [{
              value: '5-2',
              label: '5-2'
            },
              {
                value: '4-2',
                label: '4-2'
              }];
        } else if (this.productType == "块煤"){
          this.productTypeDetails = [{
            value: '3-8块',
            label: '3-8块'
          },
            {
              value: '4-9块',
              label: '4-9块'
            },
            {
              value: '6-9块',
              label: '6-9块'
            },
            {
              value: '大块',
              label: '大块'
            },
            {
              value: '中块',
              label: '中块'
            }];
        } else if (this.productType == "籽煤"){
          this.productTypeDetails = [{
            value: '1-2籽',
            label: '1-2籽'
          },
            {
              value: '1-3籽',
              label: '1-3籽'
            },
            {
              value: '2-5籽',
              label: '2-5籽'
            },
            {
              value: '3-6籽',
              label: '3-6籽'
            }];
        }

    },
    updateCoalPrice () {
      var data = {
        factoryName: this.factoryName,
        factoryType: this.factoryType,
        productType: this.productType,
        productTypeDetail: this.productTypeDetail,
        coalWashing: this.coalWashing,
        graded: this.graded,
        state: this.state,
        productType: this.productType,
        price: this.price,
        onePrice: this.onePrice,
        coal_fareliang: this.coal_fareliang,
        coal_quanshuifen: this.coal_quanshuifen,
        coal_huifafen: this.coal_huifafen,
        coal_liufen: this.coal_liufen,
        coal_huifen: this.coal_huifen
      };
      updateCoalPrice(data).then(res => {
        this.$Message.info("更新成功！");
        this.$router.push({
          name: 'coalPriceList_page'
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
