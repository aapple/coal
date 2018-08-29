<template>
  <div>
    <Card>
      <tables ref="tables"  search-place="top" v-model="tableData" :columns="columns"/>
      <Button v-if="factoryNames.length > 0" style="margin: 10px 0;" type="primary" @click="newProductType">新增兰炭种类</Button>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import { queryCoalPriceList } from '@/api/coal'
  import { queryFactoryNames } from '@/api/coal'
  export default {
    name: 'cokePriceList_page',
    components: {
      Tables
    },
    data () {
      return {
        factoryNames: [],
        columns: [
          {title: '焦化厂名称', key: 'factoryName'},
          {title: '兰炭种类', key: 'productType'},
          {title: '两票价', key: 'price'},
          {title: '一票价', key: 'onePrice'},
          {title: '更新时间', key: 'updateDate'},
          {
            title: '操作',
            key: 'action',
            width: 150,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.handleUpdate(params.row)
                    }
                  }
                }, '更新价格')
              ]);
            }
          }
        ],
        tableData: []
      }
    },
    methods: {
      handleUpdate (params) {
        params.factoryNames = this.factoryNames;
        params.updateFlag = true;
        this.$router.push({
          name: 'cokePrice_page',
          params: params
        })
      },
      newProductType () {
        var params = {};
        params.factoryNames = this.factoryNames;
        params.updateFlag = false;
        this.$router.push({
          name: 'cokePrice_page',
          params: params
        })
      }
    },
    mounted () {
      var data = {
        factoryType: "兰炭"
      };
      queryCoalPriceList(data).then(res => {
        this.tableData = res.data.data;
        resolve()
      }).catch(err => {

      })

      queryFactoryNames(data).then(res => {
        this.factoryNames = res.data;
        resolve()
      }).catch(err => {

      })

    }
  }
</script>

<style>

</style>
