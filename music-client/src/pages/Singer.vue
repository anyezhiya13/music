<template>
  <div class="singer">
    <ul class="singer-header">
      <li v-for="(item,index) in singerStyle" :key="index" @click="handleChangeView(item)" :class="{active: item.name==activeName}">
      {{item.name}}
      </li>
    </ul>
    <div>
      <content-list :contentList="data"></content-list>
      <div class="pagination">
        <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
          :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length"></el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import { getAllSinger, getSingerOfSex } from '../api/index'
import ContentList from '../components/ContentList.vue'
import { mixin } from '../mixins'
import { singerStyle } from '../assets/data/singer'
export default {
  components: {
    ContentList
  },
  name: 'singer',
  mixins: [mixin],
  data () {
    return {
      albumDatas: [], // 歌单数据
      pageSize: 10, // 页面大小，一页15条数据
      currentPage: 1, // 当前页默认第一页
      singerStyle: [], // 歌单的风格
      activeName: '全部歌手' // 当前风格

    }
  },
  computed: {
    // 计算当前页表格的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  mounted () {
    this.singerStyle = singerStyle
    this.getSingerList()
  },
  methods: {
    getSingerList () {
      getAllSinger()
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
    },
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    // 根据性别显示对应歌手
    handleChangeView (item) {
      this.activeName = item.name
      this.albumDatas = []
      if (item.name === '全部歌手') {
        this.getSingerList()
      } else {
        this.getSingerOfSex(item.type)
      }
    },
    // 根据性别查询对应歌手
    getSingerOfSex (sex) {
      getSingerOfSex(sex)
        .then(res => {
          this.currentPage = 1
          this.albumDatas = res
        })
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/singer.scss'
</style>
