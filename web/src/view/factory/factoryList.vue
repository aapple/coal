<template>
  <div>
    <Card>
      <tables ref="tables"  search-place="top" v-model="tableData" :columns="columns"/>
      <Button style="margin: 10px 0;" type="primary" @click="newProductType">新增厂矿</Button>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import { queryFactoryList } from '@/api/coal'
  export default {
    name: 'factoryList_page',
    components: {
      Tables
    },
    data () {
      return {
        columns: [
          {title: '厂矿名称', key: 'factoryName'},
          {title: '厂矿类型', key: 'factoryType'},
          {title: '管理用户名称', key: 'userName'},
          {title: '业务员名字', key: 'sallerName'},
          {title: '业务员联系电话', key: 'sallerPhone'},
          {title: '创建时间', key: 'createDate'},
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
                }, '更新厂矿')
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
          name: 'factory_page',
          params: params
        })
      },
      newProductType () {
        this.$router.push({
          name: 'factory_page',
          params: {}
        })
      }
    },
    mounted () {
      var data = {
        //factoryName: this.factoryName,
      };
      queryFactoryList(data).then(res => {
        this.tableData = res.data.data;
        resolve()
      }).catch(err => {

      })

    }
  }
</script>

<style>

</style>
