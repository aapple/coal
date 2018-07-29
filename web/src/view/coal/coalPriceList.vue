<template>
  <div>
    <Card>
      <tables ref="tables"  search-place="top" v-model="tableData" :columns="columns"/>
      <Button style="margin: 10px 0;" type="primary" @click="newProductType">新增煤炭种类</Button>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import { queryCoalPriceList } from '@/api/coal'
  export default {
    name: 'coalPriceList_page',
    components: {
      Tables
    },
    data () {
      return {
        columns: [
          {title: '煤矿名称', key: 'factoryName', sortable: true},
          {title: '煤炭种类', key: 'productType', editable: true},
          {title: '煤炭种类细分', key: 'productTypeDetail', editable: true},
          {title: '价格', key: 'price', editable: true},
          {title: '更新时间', key: 'updateDate', editable: true},
          {
            title: 'Action',
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
                }, '更新煤价')
              ]);
            }
          }
        ],
        tableData: []
      }
    },
    methods: {
      handleUpdate (params) {
        this.$router.push({
          name: 'coalPrice_page',
          params: params
        })
      },
      newProductType () {
        this.$router.push({
          name: 'coalPrice_page'
        })
      }
    },
    mounted () {
      var data = {
        //factoryName: this.factoryName,
      };
      queryCoalPriceList(data).then(res => {
        this.tableData = res.data.data;
        resolve()
      }).catch(err => {

      })

    }
  }
</script>

<style>

</style>
