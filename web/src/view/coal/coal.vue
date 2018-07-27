<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">煤矿名称</Col>
      <Col>
        <Input v-model="value4" placeholder="Enter something..." clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">煤矿名称</Col>
      <Col>
      <Input v-model="value4" placeholder="Enter something..." clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">煤矿名称</Col>
      <Col>
      <Input v-model="value4" placeholder="Enter something..." clearable style="width: 200px"></Input>
      </Col>
    </Row>

    <br>
    <br>
    <Row>
      <Button type="primary">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateCoalPrice } from '@/api/coal'
export default {
  name: 'coal',
  components: {

  },
  data () {
    return {
      value4: 'test',
      value5: 'test'
    }
  },
  methods: {
    handleSuccess () {
      this.validated = true
    },
    handleError (index) {
      this.validated = false
      this.errorIndex = index
    },
    handleShow () {
      if (!this.validated) {
        this.$Notice.error({
          title: '您的内容不规范',
          desc: `您的第${this.errorIndex + 1}行数据不规范，请修改`
        })
      } else {
        let { columns, tableData } = getTableDataFromArray(this.pasteDataArr)
        this.columns = columns
        this.tableData = tableData
      }
    }
  },
  updateCoalPrice () {
    updateCoalPrice({

    }).then(res => {
      const data = res.data

      resolve()
    }).catch(err => {

    })
  }
}
</script>

<style lang="less">
  .ivu_title {
    padding-top: 5px;
  }
</style>
