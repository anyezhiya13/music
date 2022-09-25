<template>
    <div>
        <the-header></the-header>
        <the-aside></the-aside>
        <div class="content-box" :class="{'content-collapse':collapse}">
        <router-view></router-view>
        </div>
    </div>
</template>
<script>

import TheAside from './TheAside'
import TheHeader from './TheHeader'
import bus from '../assets/js/bus'

export default {
  components: {
    TheAside,
    TheHeader
  },
  data () {
    return {
      collapse: false
    }
  },
  created () {
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>
