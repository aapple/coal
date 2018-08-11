<template>
  <div>
    <Card>
      <tables ref="tables"  search-place="top" v-model="tableData" :columns="columns"/>
      <Button style="margin: 10px 0;" type="primary" @click="newProductType">新增物流单</Button>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import { queryLogisticsList } from '@/api/coal'
  export default {
    name: 'logisticsList_page',
    components: {
      Tables
    },
    data () {
      return {
        columns: [
          {title: '物流部名称', key: 'factoryName'},
          {title: '出发地', key: 'start'},
          {title: '目的地', key: 'end'},
          {title: '用车数量', key: 'count'},
          {title: '价格', key: 'price'},
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
                }, '更新物流单')
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
          name: 'logistics_page',
          params: params
        })
      },
      newProductType () {
        this.$router.push({
          name: 'logistics_page',
          params: {}
        })
      }
    },
    mounted () {
      var data = {};
      queryLogisticsList(data).then(res => {
        this.tableData = res.data.data;
        resolve()
      }).catch(err => {

      })

    }
  }
</script>

<style>

</style>
