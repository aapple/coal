<template>
  <div>
    <Card>
      <tables ref="tables"  search-place="top" v-model="tableData" :columns="columns"/>
      <Button style="margin: 10px 0;" type="primary" @click="newProductType">新增用户</Button>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import { queryUserList } from '@/api/coal'
  export default {
    name: 'userList_page',
    components: {
      Tables
    },
    data () {
      return {
        columns: [
          {title: '用户名', key: 'userName'},
          {title: '密码', key: 'password'},
          {title: '角色', key: 'role'},
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
                }, '更新用户')
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
          name: 'user_page',
          params: params
        })
      },
      newProductType () {
        this.$router.push({
          name: 'user_page',
          params: {}
        })
      }
    },
    mounted () {
      var data = {};
      queryUserList(data).then(res => {
        this.tableData = res.data.data;
        resolve()
      }).catch(err => {

      })

    }
  }
</script>

<style>

</style>
